package utility;

import model.City;

public abstract class Element{
        private static int globalIdCounter = 1;
        protected Integer id;
        public Integer getId(){
            return id;
        }
}
