package todowizard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import org.seasar.doma.Entity;
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

    int id;

    boolean completed;

    String title;
}
