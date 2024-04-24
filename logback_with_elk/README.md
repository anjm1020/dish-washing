## TOPIC
**배경**
 - 무지성 CloudWatch 쓰다가 이틀만에 80$ 나옴  
 - 스트림으로 들어왔다 나가야할 로그가 계속 Cloudwatch에 롤링되어 있어서 요금이 많이 나옴  
 - S3로 돌릴까도 했다가, 그냥 직접 ELK로 구축 + S3로 index 백업  

**목표**
 - ELK Docker-Compose로 올려서 사용할 수 있도록 구축  

>  EC2, ELK 다른 호스트에 분리  
>  도커 네트워크 밖에서 잘 동작하는지 확인

## TODO
- [x] Kibana에 인덱스 들어오는지 확인
- [ ] Kibana visual dashboard 또는 grafana 구축
- [ ] S3에 인덱스 백업