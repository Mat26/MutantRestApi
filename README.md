# MutantRestApi

Magneto wants to recruit as many mutants as possible so he can fight the X-Men. To do this, MutantRestApi was developed that detects whether a human is a mutant based on their DNA sequence.

////Code Coverage: 96%/////

## Get if It is Mutant or not

### Request

`POST /mutant/`


    curl -X POST 'http://mutantapirest-env.eba-3eknhptq.us-east-1.elasticbeanstalk.com/mutant' \
    -H 'Content-Type: application/json' \
    -d '{"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}'

### Response(Is Mutant)

    HTTP/1.1 200 
    Server: nginx/1.18.0
    Date: Thu, 20 May 2021 05:07:40 GMT
    Content-Type: text/plain;charset=UTF-8
    Content-Length: 19
    Connection: keep-alive

    WELCOME TO THE XMEN

### Response(Is Human)

    HTTP/1.1 403 
    Server: nginx/1.18.0
    Date: Thu, 20 May 2021 05:08:36 GMT
    Content-Type: text/plain;charset=UTF-8
    Content-Length: 21
    Connection: keep-alive
    
    YOU DON'T BELONG HERE

## Statistic

### Request

`GET /stats/`

    curl http://mutantapirest-env.eba-3eknhptq.us-east-1.elasticbeanstalk.com/stats

### Response

    HTTP/1.1 200 
    Server: nginx/1.18.0
    Date: Thu, 20 May 2021 05:06:17 GMT
    Content-Type: application/json
    Transfer-Encoding: chunked
    Connection: keep-alive

    {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4} 

