package com.guild.shared.mapping;

import com.guild.events.mapping.EventMapper;
import com.guild.games.mapping.GameMapper;
import com.guild.games.mapping.GenderMapper;
import com.guild.guild.mapping.GuildMapper;
import com.guild.guild.mapping.MemberMapper;
import com.guild.guild.mapping.RolMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("Configuration")
public class MappingConfiguration {

    @Bean
    public EnhancedModelMapper modelMapper(){
        return new EnhancedModelMapper();
    }
    @Bean
    public GameMapper gameMapper(){
        return new GameMapper();
    }
    @Bean
    public GenderMapper genderMapper(){
        return new GenderMapper();
    }
    @Bean
    public GuildMapper guildMapper(){
        return new GuildMapper();
    }
    @Bean
    public RolMapper rolMapper(){
        return new RolMapper();
    }
    @Bean
    public MemberMapper memberMapper(){
        return new MemberMapper();
    }
    @Bean
    public EventMapper eventMapper(){
        return new EventMapper();
    }

    //TODO: implement Suscription mapper

}
