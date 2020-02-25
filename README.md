# prac-springboot

+ 2020/2/25<br>
  JPA Auditing을 테스트하는 과정에서 'postsRepository' bean defined in null, already has been registered ~ 에러 발생.
  
  **해결을 위해 시도한 방법**
  1. application.properties에 'spring.main.allow-bean-definition-overriding=true' 추가<br>
  **=> 또 다른 에러 발생으로 실패**
  2. 'postsRepository' 라는 이름으로 선언된 변수들의 이름을 'postsRepository2..' 등 숫자를 붙여 바꾸어 보았음 <br>
  **=> 이름을 바꾸었음에도 불구하고 'postsRepository'에 관한 에러가 발생. (postsRepository는 더 이상 존재하지 않는 상태) 실패.
  3. 최수의 방법으로 관련된 코드들을 모두 주석처리 후, 한 덩어리씩 주석을 풀며 나름의 디버깅 시도
  **=> 주석을 풀다보니 모든 주석을 풀게되었는데, 테스트코드가 정상적으로 실행되기 시작함.... 아무튼 성공.**<br>
  <br>
  관련이 있을지 모르겠지만, postsService 클래스에서 @transactional 어노테이션을 사용하기 위해 org.springframework.transaction.annotation.Transactional; 을 호출했어야 했는데 다른 라이브러리를 호출함<br>
  하지만 수정 후에도 테스트코드는 실행되지 않았었음.
  
