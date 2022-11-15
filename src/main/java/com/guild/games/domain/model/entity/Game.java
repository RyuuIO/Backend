package com.guild.games.domain.model.entity;

import com.guild.guild.domain.model.entity.Guild;
import com.guild.shared.domain.model.AuditModel;
import lombok.*;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class Game extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotBlank
    @NotBlank
    private String image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gender_id", nullable = true)
    private Gender gender = null;

    @OneToMany(mappedBy = "game")
    private List<Guild> guildList;
}
