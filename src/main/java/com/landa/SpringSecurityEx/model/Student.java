package com.landa.SpringSecurityEx.model;
import java.lang.String;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private int id;
    private String name;
    private int marks;
}
