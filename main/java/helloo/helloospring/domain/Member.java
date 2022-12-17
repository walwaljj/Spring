package helloo.helloospring.domain;

import javax.persistence.*;

@Entity//jpa가 관리하는 entity
public class Member {
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)// id = pk , @GeneratedValue 는 자동으로 값이 올라가게 하는 것
    private Long id;// 시스템이 데이터를 구분하기 위해 저장하는 id
//    @Column(name = "username")// db의 column이름. db상 매칭시킬 column이름 적어주기.
    private String name; // 고객이 등록하는 자신의 이름.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
