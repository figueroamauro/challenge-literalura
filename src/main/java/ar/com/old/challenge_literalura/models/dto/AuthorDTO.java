package ar.com.old.challenge_literalura.models.dto;

import com.google.gson.annotations.SerializedName;

public record AuthorDTO(
        @SerializedName("name")
        String name,
        @SerializedName("birth_year")
        String birthYear,
        @SerializedName("death_year")
        Integer deathYear
) {
}
