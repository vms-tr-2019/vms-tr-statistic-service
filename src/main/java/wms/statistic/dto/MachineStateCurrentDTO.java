package wms.statistic.dto;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Document(collection = PersistanceConstants.CURRENT_MACHINES_STATE_COLLECTION)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class MachineStateCurrentDTO {

  @Id
  public int machineId;
  public Map<Integer, Integer> sensorsData;

  public MachineStateCurrentDTO(int machineId, Map<Integer, Integer> sensorsData) {
    super();
    this.machineId = machineId;
    this.sensorsData = sensorsData;
  }
}
