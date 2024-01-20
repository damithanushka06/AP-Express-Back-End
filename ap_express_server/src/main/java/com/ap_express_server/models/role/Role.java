package com.ap_express_server.models.role;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role_mst")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

  public Role(int id, ERole name) {
    this.id = id;
    this.name = name;
  }

  public Role() {

  }
}