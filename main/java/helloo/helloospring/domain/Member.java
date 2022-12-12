package helloo.helloospring.domain;

public class Member {

    private Long id;// 시스템이 데이터를 구분하기 위해 저장하는 id
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
