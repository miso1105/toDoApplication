# To Do Application

### Step 2

댓글을 작성, 수정, 삭제하고 할일카드 목록을 조회하면 연관된 댓글 목록이 같이 조회되는 api


#### To Do Card API 설계
***
![image](https://github.com/miso1105/toDoApplication/assets/137920347/45ad1ab0-0c4f-4051-8d10-10f29e178f10)
![image](https://github.com/miso1105/toDoApplication/assets/137920347/f90dff67-caf8-482e-a683-d56dfca7c26b)

#### ERD
***
![image](https://github.com/miso1105/toDoApplication/assets/137920347/4a5d3ff1-cdeb-473b-bbeb-97262c8f2510)

#### Event Storming
***
![image](https://github.com/miso1105/toDoApplication/assets/137920347/988fa524-c9a3-4b4d-87d9-05eed03d5a33)



#### 단위 테스트 진행
***
![image](https://github.com/miso1105/toDoApplication/assets/137920347/fc111b79-15d2-4312-92fa-4ac999b82a67)


- 댓글 작성

![image](https://github.com/miso1105/toDoApplication/assets/137920347/fabc5c57-5c26-4de5-8ca6-8c773a0d03e2)
![image](https://github.com/miso1105/toDoApplication/assets/137920347/57852ab6-0a9b-4011-86ec-7c16ad9773d2)

- 댓글 수정

![image](https://github.com/miso1105/toDoApplication/assets/137920347/1516b99a-8936-44a0-9912-bedb0d34a79b)
![image](https://github.com/miso1105/toDoApplication/assets/137920347/c96cc5ff-8fa8-4868-a677-2a59fd36bd12)

- 댓글 삭제

![image](https://github.com/miso1105/toDoApplication/assets/137920347/f960b33c-3976-4846-b4e5-0f5197a3b82c)
![image](https://github.com/miso1105/toDoApplication/assets/137920347/7f5ae5d9-05fa-4227-8b99-23172df33684)

- 댓글 목록 아이디 기반으로 불러오기

![image](https://github.com/miso1105/toDoApplication/assets/137920347/b9d480c2-203b-4573-b844-d0840dac83f4)
![image](https://github.com/miso1105/toDoApplication/assets/137920347/8f442c15-56a8-4b98-b702-efd4713e3279)
![image](https://github.com/miso1105/toDoApplication/assets/137920347/280d0c67-f593-40e3-9c62-355ec64c8cf5)


- 할일 리스트 생성 날짜 기준 오름차순 정렬

![image](https://github.com/miso1105/toDoApplication/assets/137920347/853e7d20-8c21-4a66-88fc-a2d43fa942b8)
![image](https://github.com/miso1105/toDoApplication/assets/137920347/03fd565e-ee97-4be2-bb75-fd86d904b976)

![image](https://github.com/miso1105/toDoApplication/assets/137920347/fce45a92-45de-403c-91c2-210c15e76c45)
![image](https://github.com/miso1105/toDoApplication/assets/137920347/32c81972-b47b-4721-a7bd-3ec1b0d06d6c)


- 할일 리스트 생성 날짜 기준 내림차순 정렬

![image](https://github.com/miso1105/toDoApplication/assets/137920347/9e32d2d8-723e-4605-a537-ec3573e13562)
![image](https://github.com/miso1105/toDoApplication/assets/137920347/bc03ca72-c004-4753-8ffb-1202278b8201)

![image](https://github.com/miso1105/toDoApplication/assets/137920347/a7538de5-0747-439d-85fa-77fd51e478df)
![image](https://github.com/miso1105/toDoApplication/assets/137920347/66195e15-fab9-425c-966d-f53dc9e4f09a)

- 할일이 삭제되면 관련 댓글목록도 같이 삭제

![image](https://github.com/miso1105/toDoApplication/assets/137920347/e931984b-e556-4325-9ff4-b1083a6c5bc3)
![image](https://github.com/miso1105/toDoApplication/assets/137920347/14d5eb2f-87c3-4fd0-918e-55530f019fcf)

![image](https://github.com/miso1105/toDoApplication/assets/137920347/9e3b9b38-3ff8-4abd-a803-dd7e241a8903)
![image](https://github.com/miso1105/toDoApplication/assets/137920347/7280d436-4eb8-421a-aed2-207e86a0dc98)

- 할일 완료 기능
FALSE: 기본값으로 할일 미완료, TRUE: 할일 완료값

  - done을 입력하면 'doneStatus'가 FALSE -> TRUE
  ![image](https://github.com/miso1105/toDoApplication/assets/137920347/eeb3fcef-83ed-47ff-8669-4eda22e20b49)
  ![image](https://github.com/miso1105/toDoApplication/assets/137920347/17f4d94c-6865-451c-8a26-22920657224a)
  
  
  - not을 입력하면 'doneStatus'가 TRUE -> FALSE
  ![image](https://github.com/miso1105/toDoApplication/assets/137920347/f0906445-0999-47f3-9a7e-09107fc6ba14)
  ![image](https://github.com/miso1105/toDoApplication/assets/137920347/2870ba9e-4034-49ce-b807-a62f899017ac)
  ![image](https://github.com/miso1105/toDoApplication/assets/137920347/ef6e1fa9-23d7-4307-92a0-9ebfb1729484)

- 이용가능한 테스트용 데이터
  - 할일 포스트 데이터
  ![image](https://github.com/miso1105/toDoApplication/assets/137920347/4e90314c-6e11-4822-ab3e-fa2f7bde29e4)

  - 할일 포스트 내 댓글 데이터
![image](https://github.com/miso1105/toDoApplication/assets/137920347/3cca38c3-451a-48ff-be90-334d511d672e)



