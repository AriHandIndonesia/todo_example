package com.hand.todo.api.dto;

import com.hand.todo.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hzero.common.HZeroCacheKey;
import org.hzero.core.cache.CacheValue;
import org.hzero.core.cache.Cacheable;
import org.hzero.export.annotation.ExcelColumn;
import org.hzero.export.annotation.ExcelSheet;

import java.util.List;

@ExcelSheet(en = "User Info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends User implements Cacheable {
    @ExcelColumn(promptCode = "children", promptKey = "children", child = true)
    private List<TaskDTO> taskList;
}
