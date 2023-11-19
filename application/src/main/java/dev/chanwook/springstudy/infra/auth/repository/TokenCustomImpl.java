package dev.chanwook.springstudy.infra.auth.repository;

import java.util.List;
import java.util.stream.Collectors;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import dev.chanwook.springstudy.infra.auth.repository.entity.QTokens;
import dev.chanwook.springstudy.infra.auth.repository.entity.Tokens;

public class TokenCustomImpl implements TokenCustom {

    private final JPAQueryFactory queryFactory;

    public TokenCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Tokens> findAllValidTokenByUsername(String userName) {
        QTokens t = QTokens.tokens;

        JPAQuery<Tuple> query = queryFactory
                .select(
                		t.id,
                		t.token,
                		t.tokenType,
                		t.expired,
                		t.revoked,
                		t.username)
                .from(t)
                .where(
                		t.username.eq(userName));

        return query.fetch().stream().map(
        		tuple -> Tokens.builder()
			        .id(tuple.get(t.id))
			        .token(tuple.get(t.token))
			        .tokenType(tuple.get(t.tokenType))
			        .expired(tuple.get(t.expired))
			        .revoked(tuple.get(t.revoked))
			        .username(tuple.get(t.username)).build())
        		.collect(Collectors.toList());
    }
}
