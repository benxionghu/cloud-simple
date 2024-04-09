package com.simplemessage.cloudsimple.Infrastructure.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页相关信息
 *
 * @Author: benxiong.hu
 * @CreateAt: 2023/01/09 12:22:04
 * @ModifyAt: 2023/01/09 12:22:04
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResult<T> {
    private Long totalCount;

    private Long currentIndex;

    private Long currentPage;

    private List<T> result;
}
