package ${repositoryPackageName};

import ${modelPackageName};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ${simpleName} extends JpaRepository<${modelSimpleName}, Integer> {

}