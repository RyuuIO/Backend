package com.guild.shared.mapping;

import com.guild.games.mapping.GameMapper;
import com.guild.games.mapping.GenderMapper;
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

}
