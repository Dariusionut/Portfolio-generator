package com.portfolio_generator.app.models;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_info", nullable = true)
    @NonNull
    private String info;

    @OneToOne (cascade = {DETACH, PERSIST, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NonNull
    private User user;

    public void addUser(User user) {
        this.setUser(user);
    }

}
