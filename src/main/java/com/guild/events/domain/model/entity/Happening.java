package com.guild.events.domain.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.guild.guild.domain.model.entity.Guild;
import com.guild.shared.domain.model.AuditModel;
import com.guild.users.domain.model.entity.Player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@Entity
@Table(name = "events")
public class Happening extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime hour;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "guild_id", nullable = true)
    private Guild guild = null;

    @JoinTable(
        name = "suscriptions",
        joinColumns = @JoinColumn(name = "event_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name="user_id", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Player> players;

    //TODO: Implement enpoints

}
