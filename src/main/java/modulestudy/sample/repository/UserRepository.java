package modulestudy.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import modulestudy.sample.model.User;

//crud method는 jpa를 들고있다.
//jparepo를 상속했기때문에 IoC가 건웅
public interface UserRepository extends JpaRepository<User, Long> {

}
