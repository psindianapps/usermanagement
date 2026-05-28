package com.example.UserManagement.specification;

import com.example.UserManagement.entity.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserSpecification {

    public static Specification<UserEntity> userSpecification(Map<String, String> filter) {

        return (root, query, criteriaBuilder) -> {
            String  search = filter.get("search");
            String start = filter.get("start");
            String end = filter.get("end");

            List<Predicate> predicates = new ArrayList<>();

            if (search != null && !search.isBlank()) {
                 Predicate namePredicate =   criteriaBuilder.like(
                                                    criteriaBuilder.lower(root.get("name")),
                                                    "%" + search.toLowerCase() + "%"
                                            );

                 Predicate emailPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%"+ search.toLowerCase()+"%");
                predicates.add(
                        criteriaBuilder.or(
                                namePredicate,
                                emailPredicate

                        )
                );
            }

            if(start !=null && !start.isBlank()) {
                LocalDateTime startDate = LocalDate.parse(start).atStartOfDay();
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), startDate));
            }
            if(end !=null && !end.isBlank()) {
                LocalDateTime endDate = LocalDate.parse(end).atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), endDate));
            }

            return criteriaBuilder.and(
                    predicates.toArray(new Predicate[0])
            );
        };
    }
}
