package org.javaboy.formlogin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author xinggevip
 * @date 2020/4/20 11:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "t_role")
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String nameZh;

}
