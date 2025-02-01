/**
 * Created By Arun Singh
 * Date:30-01-2025
 * Time:10:00
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditAwareConfig implements AuditorAware<Integer> {
    @Override
    public Optional<Integer> getCurrentAuditor() {
        return Optional.of(2);
    }
}
