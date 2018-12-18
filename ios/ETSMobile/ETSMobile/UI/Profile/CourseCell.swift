//
//  CourseCell.swift
//  ETSMobile
//
//  Created by Emmanuel Proulx on 2018-12-13.
//  Copyright © 2018 ApplETS. All rights reserved.
//

import UIKit

class CourseCell: UICollectionViewCell {
    
    @IBOutlet weak var courseTitle: UILabel!
    
    @IBOutlet weak var gradeTitle: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.style()
        
    }
    
    func style() {
        self.backgroundColor = UIColor.clear
        self.layer.borderColor = UIColor.init(red: 226.00/255.00, green: 76.00/255.00, blue: 71.00/255.00, alpha: 1).cgColor
        self.layer.borderWidth = 3.0
        self.layer.cornerRadius = 8.0
        self.tintColor = UIColor.white
        self.courseTitle.backgroundColor = UIColor.init(red: 226.00/255.00, green: 76.00/255.00, blue: 71.00/255.00, alpha: 1)
        self.courseTitle.textColor = UIColor.white
    }
    
    func displayContent(title: String, grade: String) {
        courseTitle.text = title
        gradeTitle.text = grade
    }
}
