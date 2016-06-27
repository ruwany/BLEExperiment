package org.wso2.ble.eventprocessor.wrapper;

import org.wso2.ble.dto.BLE;
import org.wso2.ble.eventprocessor.core.SidhdhiQueryExecutor;
import org.wso2.ble.reader.BLEReader;

import java.util.List;
import java.util.Map;

/**
 * Created by ace on 6/27/16.
 */
public class SidhdhiWrapper {

    private static List<BLE> bleData;

    public static List<BLE> getBleData() {
        return bleData;
    }

    public static void setBleData(List<BLE> bleData) {
        SidhdhiWrapper.bleData = bleData;
    }

    public static void main(String args[]){
        String query = "@Import('iot.sample.input:1.0.0')\n" +
                "define stream dataIn (id int, timestamp long, location string);\n" +
                "\n" +
                "@Export('iot.sample.output:1.0.0')\n" +
                "define stream dataOut (action string, timestamp long);\n" +
                "\n" +
                "from every e1=dataIn[location=='loc_1'] -> e2=dataIn[location=='loc_2'] -> e3=dataIn[location=='loc_3']\n" +
                "select 'x' as action, e3.timestamp\n" +
                "insert into dataOut;";

        BLEReader blEReader = new BLEReader();
        blEReader.start();

        SidhdhiQueryExecutor queryExecutor = new SidhdhiQueryExecutor(query);
        queryExecutor.start();
    }

}
