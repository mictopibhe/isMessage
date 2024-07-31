package pl.davidduke.ismessage.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotBlank(message = "Text field should be not empty")
    String text;

    @NotBlank(message = "Tag field should be not empty")
    String tag;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User author;

}
