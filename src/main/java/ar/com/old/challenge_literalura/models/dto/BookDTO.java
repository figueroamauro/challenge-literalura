package ar.com.old.challenge_literalura.models.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public record BookDTO(

        Long id,
        @SerializedName("title")
        String title,
        @SerializedName("authors")
        List<AuthorDTO> authorList,
        @SerializedName("languages")
        List<String> languages,
        @SerializedName("download_count")
        int downloadCount
) {
}
