package com.guild.users.domain.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.guild.events.domain.model.entity.Happening;
import com.guild.guild.domain.model.entity.Member;
import com.guild.shared.domain.model.AuditModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "users")
public class Player extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String nickname;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String image;

    @OneToMany(mappedBy = "player")
    private List<Member> members;

    @ManyToMany(mappedBy = "players")
    private List<Happening> events;
    //TODO: Implement enpoints
}
