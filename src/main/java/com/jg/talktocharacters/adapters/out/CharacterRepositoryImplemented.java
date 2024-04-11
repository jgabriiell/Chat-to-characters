package com.jg.talktocharacters.adapters.out;

import com.jg.talktocharacters.domain.model.Character;
import com.jg.talktocharacters.domain.ports.CharacterRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CharacterRepositoryImplemented implements CharacterRepository {

    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Character> characterRowMapper;

    public CharacterRepositoryImplemented(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.characterRowMapper = ((rs, rowNum) -> new Character(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("lore"),
                rs.getString("role"),
                rs.getString("image_url")
        ));
    }

    @Override
    public List<Character> findAll() {
        return jdbcTemplate.query("SELECT * FROM CHARACTERS", characterRowMapper);
    }

    @Override
    public Optional<Character> findById(Long id) {
        String sql = "SELECT * FROM CHARACTERS WHERE ID = ?";
        List<Character> character = jdbcTemplate.query(sql, characterRowMapper, id);
        return character.stream().findFirst();
    }
}
