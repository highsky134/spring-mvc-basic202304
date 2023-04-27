package com.spring.mvc.etc;

import lombok.*;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actor {

    private String actorName;
    private int actorAge;
    private boolean hasPhone;
}
