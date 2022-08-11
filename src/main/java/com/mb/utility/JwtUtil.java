package com.mb.utility;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil
{
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	@Value("${jwtSecret}")
	private String jwtSecret;

	@Value("${jwtExpirationMs}")
	private int jwtExpirationMs;

	public String extractUsername(String token)
	{
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token)
	{
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
	{
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public Claims extractAllClaims(String token)
	{
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token)
	{
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(String userName, Map<String, Object> claims, String role)
	{
		return createToken(claims, userName, role);
	}

	private String createToken(Map<String, Object> claims, String subject, String role)
	{

		GrantedAuthority userRole = new SimpleGrantedAuthority(role);

		return Jwts.builder().setClaims(claims).claim("scope", userRole.getAuthority()).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 200))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails)
	{
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	// public String generateJwtToken(Authentication authentication)
	// {
	// CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
	// String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
	//
	// return Jwts.builder()
	// .setSubject(userPrincipal.getUsername())
	// .setIssuedAt(new Date(System.currentTimeMillis()))
	// .setExpiration(new Date((new Date().getTime() + jwtExpirationMs)))
	// .claim("roles", authorities)
	// .signWith(SignatureAlgorithm.HS512, jwtSecret)
	// .compact();
	// }
	//
	// public Claims getJwtClaims(String jwtToken)
	// {
	// Claims claims = null;
	// try
	// {
	// claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody();
	// }
	// catch (ExpiredJwtException e)
	// {
	// throw new CustomException("Token is Expired");
	// }
	// catch (SignatureException | MalformedJwtException e)
	// {
	// throw new CustomException("Invalid Token");
	// }
	// catch (Exception e)
	// {
	// throw new CustomException("Internal Error while parsing token ");
	// }
	// return claims;
	// }
	//
	// public String getUserNameFromJwtToken(String token)
	// {
	// return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	// }
	//
	// public boolean validateJwtToken(String token)
	// {
	// try
	// {
	// Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
	// return true;
	// }
	// catch (SignatureException e)
	// {
	// logger.error("Invalid JWT signature: {}", e.getMessage());
	// }
	// catch (MalformedJwtException e)
	// {
	// logger.error("Invalid JWT token: {}", e.getMessage());
	// }
	// catch (ExpiredJwtException e)
	// {
	// logger.error("JWT token is expired: {}", e.getMessage());
	// }
	// catch (UnsupportedJwtException e)
	// {
	// logger.error("JWT token is unsupported: {}", e.getMessage());
	// }
	// catch (IllegalArgumentException e)
	// {
	// logger.error("JWT claims string is empty: {}", e.getMessage());
	// }
	//
	// return false;
	//
	// }
}
