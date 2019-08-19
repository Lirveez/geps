package mirea.geps.core.service.db;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Data
@Builder
public class FileDb {

    @Id
    @GeneratedValue
    private String id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDb user;
}
