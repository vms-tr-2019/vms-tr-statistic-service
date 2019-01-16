package wms.statistic.dto;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import wms.statistic.jpa.ProductJPA;


@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductDTO {

  public int productId;
  @NotNull
  public String name;
  @NotNull
  public int price;
  public boolean avaliable = true;

  public ProductDTO(int productId, String name, int price, boolean avaliable) {
    super();
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.avaliable = avaliable;
  }

  public ProductJPA toProductJPA() {
    return new ProductJPA(this.productId, this.name, this.price, this.avaliable);
  }
}
