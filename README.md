# MutantRestApi

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men. Para esto se desarrollo MutantRestApi que detecta si un humano es mutante basándose en su secuencia de ADN.


## Get if It is Mutant or not

### Request

`POST /mutant/`


    curl -X POST 'http://localhost:8080/mutant' \ 
    -H 'Content-Type: application/json' \ 
    -d '{ "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] } 

### Response(Is Mutant)

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 2

    WELCOME TO THE XMEN

### Response(Is Human)

    HTTP/1.1 403 Forbidden
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 403 Forbidden
    Connection: close
    Content-Type: application/json
    Content-Length: 2
    
    YOU DON'T BELONG HERE

## Statistic

### Request

`GET /stats/`

    curl http://mutantapirest-env.eba-3eknhptq.us-east-1.elasticbeanstalk.com/stats

### Response

    HTTP/1.1 200 OK
    Date: Wed, 19 May 2021 04:53:22 GMT
    Status: 200 OK
    Connection: keep-alive
    Content-Type: application/json
    Location: /thing/1
    Content-Length: 36

    {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4} 

