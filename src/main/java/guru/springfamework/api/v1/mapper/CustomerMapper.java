package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    static final String URL_PREFIX = "/api/v1/customers/";

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "customerUrl", expression = "java(getCustomerUrl(customer.getId()))")
    CustomerDTO customerToCustomerDTO(Customer customer);

    default String getCustomerUrl(Long id) {
        return URL_PREFIX + id;
    }
}
