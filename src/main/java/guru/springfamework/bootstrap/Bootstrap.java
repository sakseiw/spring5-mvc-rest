package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRespository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRespository, CustomerRepository customerRepository) {
        this.categoryRespository = categoryRespository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();


        log.info("Categories Loaded = " + categoryRespository.count() );
        log.info("Customers Loaded = " + categoryRespository.count() );

    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRespository.save(fruits);
        categoryRespository.save(dried);
        categoryRespository.save(fresh);
        categoryRespository.save(exotic);
        categoryRespository.save(nuts);
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstname("John");
        customer1.setLastname("Snow");

        Customer customer2 = new Customer();
        customer2.setFirstname("Luke");
        customer2.setLastname("Skywalker");

        Customer customer3 = new Customer();
        customer3.setFirstname("Indiana");
        customer3.setLastname("Jones");

        Customer customer4 = new Customer();
        customer4.setFirstname("Papa");
        customer4.setLastname("Jones");

        Customer customer5 = new Customer();
        customer5.setFirstname("Clark");
        customer5.setLastname("Kent");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);


    }
}
