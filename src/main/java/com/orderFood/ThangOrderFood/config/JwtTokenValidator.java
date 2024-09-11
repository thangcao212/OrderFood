package com.orderFood.ThangOrderFood.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

public class JwtTokenValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       //: Đoạn mã này lấy giá trị JWT từ tiêu đề của yêu cầu HTTP.
        // Giá trị của tiêu đề này được xác định bởi JwtConstant.JWT_HEADER.
        String jwt= request.getHeader(JwtConstant.JWT_HEADER);
        if(jwt!=null){
            //JWT thường được gửi dưới dạng "Bearer [token]". Do đó, đoạn mã này loại bỏ chữ "Bearer " khỏi đầu chuỗi JWT.
            jwt=jwt.substring(7);

            try{
                //: Đoạn mã này tạo ra khóa bí mật (secret key) từ một chuỗi ký tự được xác định trong
                SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
                //Đoạn mã này sử dụng khóa bí mật để xác thực JWT và trích xuất các thông tin (claims) bên trong JWT nếu nó hợp lệ.
                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

                String email = String.valueOf(claims.get("email"));//Lấy email từ thông tin trong JWT.
                String authorities =String.valueOf(claims.get("authorities"));// Lấy danh sách quyền hạn (roles) từ JWT dưới dạng chuỗi.

       // ROLE_CUSTOMER,ROLE_ADMIN
                // Chuyển đổi danh sách quyền hạn (roles) từ chuỗi phân tách bằng dấu phẩy thành danh sách các đối tượng GrantedAuthority.
                List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                //Tạo một đối tượng xác thực Authentication với email và danh sách quyền hạn.
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null,auth);
                //Thiết lập đối tượng xác thực vào ngữ cảnh bảo mật để hệ thống có thể biết rằng người dùng này đã được xác thực.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch(Exception e){
                throw new BadCredentialsException("invalid token ----");
            }
        }

        filterChain.doFilter(request, response);





    }
}
