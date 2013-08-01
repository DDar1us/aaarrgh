consulta sigo

select persona.nombre, tweet.tweet, tweet.tiempo, sigo.estado from tweet
inner join persona on persona.id = tweet.idusuario
inner join sigo on sigo.idsigo = tweet.idusuario
where sigo.idusuario = 2

consulta me siguen

select persona.nombre, tweet.tweet, tweet.tiempo, meSiguen.estado from tweet
inner join persona on persona.id = tweet.idusuario
inner join meSiguen on meSiguen.idmeSiguen = tweet.idusuario
where meSiguen.idusuario = 2

todos mis tweets y a quines sigo

select persona.nombre, tweet.tweet, tweet.tiempo from tweet
inner join persona on persona.id = tweet.idusuario
where tweet.idusuario=1
union
select persona.nombre, tweet.tweet, tweet.tiempo from tweet
inner join persona on persona.id = tweet.idusuario
inner join sigo on sigo.idsigo = tweet.idusuario
where sigo.idusuario=1 and sigo.estado=2

order by tweet.tiempo desc