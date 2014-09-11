package todowizard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.SequenceGenerator;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * Todo エンティティ
 * 
 * @author t_endo
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "todo_id_seq", initialValue = 1)
    int id;

    boolean completed;

    String title;
}