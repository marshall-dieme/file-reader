package com.opentech.filereader.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MyModel {

  @Id
  private String id;

  private String attr1;

  private String attr2;

  private String attr3;

  private String attr4;
}
