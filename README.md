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


- 할일이 삭제되면 관련 댓글목록도 같이 삭제

![image](https://github.com/miso1105/toDoApplication/assets/137920347/e931984b-e556-4325-9ff4-b1083a6c5bc3)
![image](https://github.com/miso1105/toDoApplication/assets/137920347/14d5eb2f-87c3-4fd0-918e-55530f019fcf)

![image](https://github.com/miso1105/toDoApplication/assets/137920347/9e3b9b38-3ff8-4abd-a803-dd7e241a8903)
![image](https://github.com/miso1105/toDoApplication/assets/137920347/7280d436-4eb8-421a-aed2-207e86a0dc98)

- 할일 완료 기능

  FALSE: 기본값으로 할일 미완료 상태, TRUE: 할일 완료 상태

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


- 할일 리스트 생성 날짜 기준 오름차순 정렬(수정)
  
  - 요청 값 sortedByDate에 asc or ASC 를 입력하면 오름차순 정렬 
    Request URL : http://localhost:8080/todos?sortedByDate=asc
    
    ![image](https://github.com/miso1105/toDoApplication/assets/137920347/aa28f19d-c1c4-4563-9ed3-5ba9e1088d6f)
    ![image](https://github.com/miso1105/toDoApplication/assets/137920347/b176a282-f834-46ab-b901-823b0ed52ae4)

    ![image](https://github.com/miso1105/toDoApplication/assets/137920347/0fd9de25-c159-46b4-bd5b-b6d95f0da815)
    ![image](https://github.com/miso1105/toDoApplication/assets/137920347/339c13e6-15c1-4e45-a5f3-494d550aec05)
    ![image](https://github.com/miso1105/toDoApplication/assets/137920347/25bccb5e-cfde-4861-bff0-eabb90c3243a)




- 할일 리스트 생성 날짜 기준 내림차순 정렬(수정)
  
  - 요청 값 sortedByDate에 desc or DESC 를 입력하면 내림차순 정렬 
    Request URL : http://localhost:8080/todos?sortedByDate=DESC
    
    ![image](https://github.com/miso1105/toDoApplication/assets/137920347/5f7ef8c3-f703-4634-ab5b-77977ae63162)
    ![image](https://github.com/miso1105/toDoApplication/assets/137920347/d3573496-146b-43d9-af63-68e48f957895)
    ![image](https://github.com/miso1105/toDoApplication/assets/137920347/68358f3a-65b8-456f-9b41-bbdf0221aa87)

    ![image](https://github.com/miso1105/toDoApplication/assets/137920347/7f435dd8-6db4-4ec5-9b0a-e9d27307cb6b)
    ![image](https://github.com/miso1105/toDoApplication/assets/137920347/3972764f-f8eb-449a-84d9-07254f016ecc)
    ![image](https://github.com/miso1105/toDoApplication/assets/137920347/d916c59f-d787-4a3d-a38e-b5fc8aff7fd0)



