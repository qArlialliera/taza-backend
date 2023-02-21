package com.petiveriaalliacea.taza.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

import static com.petiveriaalliacea.taza.utils.Constants.DATABASE_PREFIX;

@Table(name = DATABASE_PREFIX + "file_data")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileData {
    @Id
    private UUID id;
    private String name;
}
