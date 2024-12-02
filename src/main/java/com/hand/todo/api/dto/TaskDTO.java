package com.hand.todo.api.dto;

import com.hand.todo.domain.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hzero.export.annotation.ExcelSheet;

import java.util.List;

@ExcelSheet(en = "Task Info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TaskDTO extends Task {
    private List<Long> empIdList;
}
