package com.landa.SpringSecurityEx.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Student {
    private int id;
    private String name;
    private int marks;
}
