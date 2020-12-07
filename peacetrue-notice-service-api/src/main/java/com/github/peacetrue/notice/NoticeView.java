package com.github.peacetrue.notice;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * @author xiayx
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoticeView implements Serializable {

    private static final long serialVersionUID = 0L;

    @NotNull
    private Long id;

}
