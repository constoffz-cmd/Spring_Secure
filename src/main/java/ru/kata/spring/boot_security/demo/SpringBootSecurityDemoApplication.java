package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Collections;
import java.util.HashSet;
import java.util.logging.Logger;


@SpringBootApplication
public class SpringBootSecurityDemoApplication implements CommandLineRunner {

	private final UserService userService;
	private final RoleRepository roleRepository;
	private Logger logger = Logger.getLogger(SpringBootSecurityDemoApplication.class.getName());

	@Autowired
	public SpringBootSecurityDemoApplication(UserService userService, RoleRepository roleRepository) {
		this.userService = userService;
        this.roleRepository = roleRepository;
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

	// Этот метод автоматически запустится ПОСЛЕ старта приложения
	@Override
	public void run(String... args) throws Exception {

		// Проверяем, есть ли уже админ (используем ваш метод findByUsername)

		if (userService.findByUsername("admin2") == null) {
			User admin = new User();
			admin.setUsername("admin2");
			admin.setPassword("admin");
			Role userRole = roleRepository.findByName("ROLE_ADMIN");
			admin.setRoles(Collections.singleton(userRole));

			// Пароль в чистом виде, saveUser его захеширует
			admin.setEmail("admin2@mail.ru");
			admin.setFirstName("Admin2");
			admin.setLastName("Adminov2");

			// Метод saveUser из вашего сервиса захеширует пароль и даст ROLE_USER
			userService.saveUser(admin);

			logger.info("ТЕСТОВЫЙ ПОЛЬЗОВАТЕЛЬ СОЗДАН: admin / admin");


		}
	}
}
