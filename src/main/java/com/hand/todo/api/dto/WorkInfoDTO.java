package com.hand.todo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkInfoDTO {
//[
//    {
//        "countNumber": "TEST-47835",
//            "countStatus": "DRAFT",
//            "workFlowId": 1
//    }
//]
    private String countNumber;
    private String countStatus;
    private Long workFlowId;
}
