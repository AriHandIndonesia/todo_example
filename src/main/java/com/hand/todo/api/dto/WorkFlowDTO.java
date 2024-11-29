package com.hand.todo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkFlowDTO {

// Variable Example
//    workflowClient.startInstanceByFlowKey(Long tenantId, String flowKey, String businessKey, String dimension, String starter, Map<String, Object> variableMap);
//    "flowCode": "FLOW1861977042564792321",
//            "businessKey": "TEST_47835_0001",
//            "dimension": "EMPLOYEE",
//            "starter": "47835",
//            "processVariables": [
//    {
//        "variableId": 3971,
//            "variableCode": "DEMO_326",
//            "variableName": "目录变量2",
//            "variableType": "String",
//            "sourceType": "DEFAULT",
//            "requireFlag": 0,
//            "variableValue": "TEST_47835_1",
//            "rangeType": "CONSTANT",
//            "rangeLovCode": null
//    },
//    {
//        "variableId": 4029,
//            "variableCode": "amount",
//            "variableName": "amount",
//            "variableType": "Long",
//            "sourceType": "DEFAULT",
//            "requireFlag": 0,
//            "variableValue": 5000,
//            "rangeType": null,
//            "rangeLovCode": null
//    },
//    {
//        "variableId": 3962,
//            "variableCode": "applyDescription",
//            "variableName": "申请说明",
//            "variableType": "String",
//            "sourceType": "SYSTEM",
//            "requireFlag": 0,
//            "rangeType": "CONSTANT",
//            "rangeLovCode": null
//    },
//    {
//        "variableId": 4009,
//            "variableCode": "employeeId",
//            "variableName": "员工ID",
//            "variableType": "Long",
//            "sourceType": "DEFAULT",
//            "requireFlag": 0,
//            "rangeType": "CONSTANT",
//            "rangeLovCode": null
//    }
//    ]

    private String flowKey;
    private String bussinessKey;
    private String dimension;
    private String starter;
    private Map<String, Object> variableMap;
}
