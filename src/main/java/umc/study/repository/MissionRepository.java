package umc.study.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Mission;
import umc.study.domain.Store;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findByStore(Store store, Pageable pageable);
}
