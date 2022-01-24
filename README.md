RE Road(AWS 환경에서 구현하기)
============
🧐프로젝트 설명
---------
웹 페이지 : http://ec2-13-125-26-218.ap-northeast-2.compute.amazonaws.com/main

체계적이고 완벽한 여행을 계획하는 것은 생각보다 많은 정보를 요구한다.

여행지에 관한 정보를 얻기 위해서는 블로그 및 여행 관련 카페, 해당 지역의 날씨정보는 기상청 사이트에서, 기차 및 버스편에 대한 정보는 코레일 및 고속버스 통합예매 사이트에 찾아봐야 한다.
이처럼 원하는 정보들은 각자 다른 곳에 위치해 있기때문에 여러번의 수고스러움을 경험해야만 했다.

여행을 계획하는데 필요한 정보들을 모두 모아 볼 수 있는 웹페이지이며 본인만의 플래너를 만들어 반드시 주의해야 할 사항 또는 잊지 않고 챙겨야할 체크리스트를 만들어 유용성을 더했다. 
또한 게시글의 댓글 기능과 채팅, 쪽지 기능을 추가하여 사용자들 간의 원활한 소통으로 보다 쉽게 정보를 공유하고 얻을 수 있도록 하였다.

시스템 환경
--------
![image](https://user-images.githubusercontent.com/92851140/150704833-73df31e0-4269-49d4-8ce0-cc62012c01a8.png)


하드웨어
---------
![image](https://user-images.githubusercontent.com/92851140/150704935-8dbcdd84-e99b-4ba9-a80b-fea2c9139ebd.png)




1. 여행리뷰 게시판 구현(수정, 삭제, 작성, 댓글)
 <img style="width : 500px; height: 700px;" src= "https://user-images.githubusercontent.com/92851140/150690952-5851ee80-b013-4c12-bdaf-611f4ab95855.png" >

2. 댓글시 Stomp를 활용한 웹소켓을 이용하여 실시간 알림 구현
<img src= "https://user-images.githubusercontent.com/92851140/150689559-084a8db9-acc3-4d3d-9610-d600d6425d7f.png" >


3. 메인페이지 하단 국내여행지(지도, 해당지역 날씨정보)
<img style="width : 500px; height: 700px;" src= "https://user-images.githubusercontent.com/92851140/150690541-0e5b033c-7c84-4b90-b4e3-dbfd30d19010.png" >
