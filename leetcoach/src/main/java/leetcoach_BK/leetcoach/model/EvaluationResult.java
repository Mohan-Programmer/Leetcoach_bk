package leetcoach_BK.leetcoach.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
     * Result holder class
     */

     @Getter
     @Setter
     @NoArgsConstructor
    public  class EvaluationResult {
        public boolean isCorrect;
        public double runtime;
        public double memory;
        public String output;

        public EvaluationResult(boolean isCorrect, double runtime, double memory, String output) {
            this.isCorrect = isCorrect;
            this.runtime = runtime;
            this.memory = memory;
            this.output = output;
        }
    }