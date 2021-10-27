package com.quicksed.accounting_of_finances_app.repository.specification;

import com.quicksed.accounting_of_finances_app.dto.user.filter.UserFilterDto;
import com.quicksed.accounting_of_finances_app.entity.Role;
import com.quicksed.accounting_of_finances_app.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public class UserSpecification {

    @SuppressWarnings("unchecked")
    public static Specification<User> findUsers(Collection<UserFilterDto> filters) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();

            Fetch<User, Role> rolesFetch = root.fetch("roles", JoinType.LEFT);
            Join<User, Role> rolesJoin = (Join<User, Role>) rolesFetch;

            for (var filter : filters) {
                switch (filter.getUserField()) {
                    case ID -> predicates.add(criteriaBuilder.in(root.get("id")));

                    case EMAIL -> predicates.add(criteriaBuilder.in(root.get("email")));

                    case ROLES -> predicates.add(criteriaBuilder.in(rolesJoin.get("code")));

                    default -> throw new IllegalArgumentException();
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
