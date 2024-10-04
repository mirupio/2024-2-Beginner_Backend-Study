JPA (Java Persistence API)
= 데이터베이스에서 읽어온 데이터를 자바 객체로 매핑하는 자바의 표준 기술(ORM)

- 레코드(테이블 데이터 1개) : 엔티티 객체 1개
- 엔티티 클래스 정의 -> JPA가 SQL 알아서 작성, 실행 -> 테이블 생성

@Entity 클래스 -> 테이블 
클래스 필드 -> 컬럼, @Column -> 이름,타입 명시
@Id -> PK 필드, @GeneratedValue -> id값 자동 생성

@ManyToOne/@OneToOne/@OneToMany -> 엔티티 연관관계, fetch -> 연결 엔티티 언제 가져올지
, @JoinColumn -> 외래키 컬럼 정보 
=> 외래키 컬럼

@NoArgesConstructor,@Getter -> 생성자

![image](https://github.com/user-attachments/assets/1d6cb8af-db86-4c10-a376-ca9a9458bae4)
